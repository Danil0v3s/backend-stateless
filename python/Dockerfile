FROM python:3.6.1-alpine
RUN apk update \
    && apk add \
    build-base \
    libpq \
    libxml2-dev \
    libxslt-dev \
    python-dev \
    openssl-dev \
    libffi-dev \
    zlib-dev \
    py-pip
RUN mkdir /usr/src/app
COPY ./python/src /usr/src/app/
RUN pip install --upgrade pip
RUN pip install -r /usr/src/app/requirements.txt
WORKDIR /usr/src/app
ENTRYPOINT ["python3", "main.py"]