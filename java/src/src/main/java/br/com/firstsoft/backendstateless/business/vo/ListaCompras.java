package br.com.firstsoft.backendstateless.business.vo;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ListaCompras {

    private UUID listaComprasID;
    private List<Item> itemList;

}
