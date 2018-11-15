# recimg
Sistema Web de Reconhecimento de Padrão em Imagem utilizando algoritmo de correlação
Esse sistema foi desenvolvido para reconhecer qualquer padrão com o algoritmo, e foi testado com a aplicação real de reconhecimeno de placas de patrimônio da FATEC-SJC.

Esse servidor desenvolvido da forma mais clássica possui um algoritmo de reconhecimento de padrões em imagens com representação por região e utiliza de correlação para realizar o reconhecimento.

## Servidor apache-tomcat-8.5.20/
Para a execução necessita compilar um .war e disponibilizar no pasta webapp do servidor Apache Tomcat

Para executar o Apache Tomcat (em Dist Linux), execute o script dessa forma (aonde "DIR_TOMCAT" é diretório aonde encontra-se instalado o Apache Tomcat):
```
./DIR_TOMCAT/apache-tomcat-8.5.20/bin/startup.sh
```
## O Sistema de gerenciamento de banco de dados utilizado é o MongoDB
Esse SGBD é tipo NoSql e é orientado a documentos

### Modelo de Banco de Dados utilizado (MongoDB)

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/Screenshot%20from%202018-11-13%2001-04-05.png)

As coleções padrões podem ser restauradas pelo comando:
```
mongorestore -d <database_name> <directory_backup>

mongorestore -d recimg mongodb/recimg
```

[Diretório com as coleções de documentos do sistema](https://github.com/skatesham/tg-rec-img/tree/master/mongodb/recimg)

## Dependencias

Essas devem estár na ṕasta /lib e estarem ativar no projeto na execução.

  1. [lib/commons-math3-3.6.1.jar](http://commons.apache.org/proper/commons-math/download_math.cgi)
  2. [lib/mongo-java-driver-3.6.3.jar](https://jar-download.com/?search_box=mongo-java-driver-3.6.3)
  3. [JFreeChart](https://sourceforge.net/projects/jfreechart/files/3.%20JCommon/)
      - lib/jfreechart.jar
      - lib/jfreechart-1.0.19.jar
      - lib/jcommon-1.0.23.jar 
      - lib/swtgraphics2d.jar
     
Ou essas podem ser adicionadas ao Maven ou Gradle para facilitar o processo.

## Iniciar um cliente

Para abrir página com o servidor (após o Tomcat, MongoDB e com as dependencias necessárias. Esses configurado e executando), execute o comando (Linux Dist):
```
firefox -new-tab -url http://localhost:8080/recimg/
```

## Diagramas de Classes do Sistema
Essa área irá explicar melhor a arquitetura utilizada

### Classes Modelos do Sistema

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/CLASSES%20MODELO.png)

### Classes de Reconhecimento

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/Diagrama%20Classes%20Algoritmo.png)

### Classes de Acesso aos dados

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/Diagramas%20de%20Classes%20DAO.png)

## Exemplo de Modelo de Imagens para Reconhecimento

![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/Invese%20bin%20placas%20patrimonio.png)

## Gŕafico de Análise de resultado de reconhecimento
Esse gráfico mostra os valores dos resultado  de correlação encontrados nos valores possíveis de encaixe das imagens acima. Esse valores vão de -100 a 100, que representam correlação -1 a 1. Dessa forma, é possivel analisar o resultado de cada número em dado local da imagem variando nos valores da lagura (X) do maior resultado encontrado para aquele padrão.

<!--![alt text](https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/REAL%20Grafico%20Delta%20X.png)-->
<img src="https://raw.githubusercontent.com/skatesham/tg-rec-img/master/web/static/img/readme/REAL%20Grafico%20Delta%20X.png" width="100%" height="auto">

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

##### Foi utilizado como referência:

> GONZALEZ, Rafael C. ..; WOODS, Richard Eugene. Digital Image Processing. 3rd. ed. New Jersey.

