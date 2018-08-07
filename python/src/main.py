import lxml.html, json, os, requests, re, unicodedata, re
from flask import Flask
from flask import request
from flask import jsonify
from lxml import etree

app = Flask(__name__)
session = requests.Session()
nfeUrl = 'http://dec.fazenda.df.gov.br/autoUso.aspx?cmd=9&doc='

def formatWord(word):
    nfkd = unicodedata.normalize('NFKD', str(word))
    normalizedWord = u"".join([c for c in nfkd if not unicodedata.combining(c)])
    normalizedWord = re.sub('[^a-zA-Z0-9 \\\]', '', normalizedWord)
    normalizedWord = ''.join(normalizedWord.split())
    return normalizedWord

def scrap_page(url):
    session.get(url)
    hxs = lxml.html.document_fromstring(session.get(nfeUrl).content)
    nfe = {}

    root = hxs.xpath('//table')

    cabecalho = root[1][1]
    dadosNfe = root[3]

    nfe['DadosBasicos'] = {}
    nfe['Itens'] = []
    nfe['Emitente'] = {}

    for linha in cabecalho:
        prop = formatWord(linha.xpath(".//span[@class='TextoFundoBrancoNegrito']/text()")[0])
        value = linha.xpath(".//span[@class='linha']/text()")[0]
        nfe[prop] = re.sub(' +', ' ', value)

    for linha in dadosNfe:
        props = linha.xpath(".//span[@class='TextoFundoBrancoNegrito']/text()")
        values = linha.xpath(".//span[@class='linha']/text()")
        for p, v in zip(props, values):
            prop = formatWord(p)
            value = v.strip()
            nfe['DadosBasicos'][prop] = re.sub(' +', ' ', value)
    
    for item_table_header_button in hxs.xpath("//img[@src='img/ico_menos.gif']"):
        detail_id = item_table_header_button.get('id')[3:]

        basic_details = item_table_header_button.xpath("./parent::*/parent::*")
        item = {}
        for linha in basic_details:
            props = linha.xpath(".//span[@class='TextoFundoBrancoNegrito']/text()")
            values = linha.xpath(".//span[@class='linha']/text()")

            for p, v in zip(props, values):
                prop = formatWord(p)
                value = v.strip()
                item[prop] = re.sub(' +', ' ', value)
            
            item['Detalhes'] = {}
            for linha in hxs.xpath("//div[@id='"+ str(detail_id) +"']"):
                props = linha.xpath(".//span[@class='TextoFundoBrancoNegrito']/text()")
                values = linha.xpath(".//span[@class='linha']/text()")

                for p, v in zip(props, values):
                    prop = formatWord(p)
                    value = v.strip()
                    item['Detalhes'][prop] = re.sub(' +', ' ', value)
            
            nfe['Itens'].append(item)
        
    for emitente in (hxs.xpath(".//span[text()='Nome / Razão Social']")[2]).xpath(".//parent::*/parent::*/parent::*"):
        props = emitente.xpath(".//span[@class='TextoFundoBrancoNegrito']/text()")
        values = emitente.xpath(".//span[@class='linha']/text()")
        for p, v in zip(props, values):
            prop = formatWord(p)
            value = v.strip()
            nfe['Emitente'][prop] = re.sub(' +', ' ', value)

    return nfe

def df():
    return "http://dec.fazenda.df.gov.br/ConsultarNFCe.aspx?"


@app.route('/')
def get():
    scan_request = {
        "chNFe": request.args.get("chNFe"),
        "nVersao": request.args.get("nVersao"),
        "tpAmb": request.args.get("tpAmb"),
        "dhEmi": request.args.get("dhEmi"),
        "vNF": request.args.get("vNF"),
        "vICMS": request.args.get("vICMS"),
        "digVal": request.args.get("digVal"),
        "cIdToken": request.args.get("cIdToken"),
        "cHashQRCode": request.args.get("cHashQRCode")
    }
    nfe = {}
    if scan_request is not None:
        requestedUF = request.args.get("requestedUF")
        if requestedUF is not None:
            options = {
                "DF": df
            }

            base_url = options[requestedUF]()
            params = ["{}={}".format(key, scan_request[key]) for key in scan_request]
            url = "{}{}".format(base_url, '&'.join(params))
            print(url)
            nfe = scrap_page(url)
        else:
            return 'UF invalida'

    else:
        return 'Url invalida'

    return jsonify(nfe)


if __name__ == '__main__':
    port = int(os.environ.get('PORT', 5000))
    app.run(host='0.0.0.0', port=port, debug=True)
