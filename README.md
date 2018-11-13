# recimg
Sistema de Reconhecimento de Padrão em Imagem

Esse servidor desenvolvido da forma mais clássica possui um algoritmo de reconhecimento de padrões em imagens com representação por região e utiliza de correlação para realizar o reconhecimento.

## O Sistema de gerenciamento de banco de dados utilizado é o MongoDB
Esse SGBD é tipo NoSql e é orientado a documentos

## Servidor apache-tomcat-8.5.20/
Para a execução necessita compilar um .war e disponibilizar no pasta webapp do servidor Apache Tomcat

Para executar o Apache Tomcat (em Dist Linux), execute o script dessa forma (aonde "DIR_TOMCAT" é diretório aonde encontra-se instalado o Apache Tomcat):
```
./DIR_TOMCAT/apache-tomcat-8.5.20/bin/startup.sh
```
Para abrir página com o servidor (após o Tomcat e MongoDB configurado e executando), execute o comando (Linux Dist):
```
firefox -new-tab -url http://localhost:8080/recimg/
```

### Modelo de Banco de Dados utilizado

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/Screenshot%20from%202018-11-13%2001-04-05.png)

As coleções padrões podem ser restauradas pelo comando:
```
mongorestore -d <database_name> <directory_backup>

mongorestore -d recimg mongodb/recimg
```

[Collections](https://github.com/skatesham/tg-rec-img/tree/master/mongodb/recimg)


## Diagramas de Classes do Sistema

### Classes Modelos do Sistema

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/CLASSES%20MODELO.png)

### Classes de Reconhecimento

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/Diagrama%20Classes%20Algoritmo.png)

### Classes de Acesso aos dados

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/Diagramas%20de%20Classes%20DAO.png)

## Exemplo de Modelo de Imagens para Reconhecimento

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/Invese%20bin%20placas%20patrimonio.png)

## Gŕafico de Análise de resultado de reconhecimento
Esse gráfico mostra os valores dos resultado  de correlação encontrados nos valores possíveis de encaixe das imagens acima.

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/REAL%20Grafico%20Delta%20X.png)

## Dependencias

  1. /lib/commons-math3-3.6.1.jar
  2. /lib/jcommon-1.0.23.jar
  3. /lib/jfreechart.jar
  4. /lib/jfreechart-1.0.19.jar
  5. /lib/mongo-java-driver-3.6.3.jar
  6. /lib/swtgraphics2d.jar

## Requisitos Funcionais

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/diagrama%20caso%20de%20uso%20com%20extends.png)

## Imagens do Sistema

### Login
![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/login.png)

### Galeria
![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/galeria.png)

### Informações
![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/info.png)

### Reconhecimento unico padrão
![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/rec-pad.png)

### Reconhecimento Sequencia de padrões
![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/red-seq-pad.png)

### Desenvolvido por Sham Vinicius Fiorin

