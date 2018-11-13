/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function criarImagem(id, nome, imagem) {
    lu = document.getElementById("lista_imagens");
    p = document.createElement("p");
    p.innerHTML = id + ": " + nome;
    input = document.createElement('input');
    input.setAttribute('type', "submit");
    input.setAttribute('value', "Info");
    form = document.createElement("form");
    form.appendChild(input);
    form.setAttribute("method", "post");
    form.setAttribute("action", "Reconhecimento");
    img = document.createElement("img");
    img.setAttribute('src', imagem);
    img.setAttribute('width', "80px");
    img.setAttribute('height', "80px");
    img.setAttribute('title', nome);
    li = document.createElement("li");
    li.setAttribute('display', "inline");
    li.appendChild(img);
    li.appendChild(p);
    li.appendChild(form);
    li.appendChild(document.createElement("br"));
    lu.appendChild(li);
}




function criarListaDeImagens() {
    lu = document.getElementById("lista_imagens");
    for (i = 0; i < 10; i++) {
        nome = "Minha Imagem ";
        p = document.createElement("p");
        p.innerHTML = nome + i;
        input = document.createElement('input');
        input.setAttribute('type', "submit");
        input.setAttribute('value', "Info");
        form = document.createElement("form");
        form.setAttribute('method', "get");
        form.setAttribute('action', 'dados_da_imagem.html');
        form.appendChild(input);
        img = document.createElement("img");
        img.setAttribute('src', "img_padrao.jpg");
        img.setAttribute('width', "50px");
        img.setAttribute('height', "50px");
        img.setAttribute('title', nome + i);
        li = document.createElement("li");
        li.setAttribute('display', "inline");
        li.appendChild(img);
        li.appendChild(p);
        li.appendChild(form);
        li.appendChild(document.createElement("br"));
        lu.appendChild(li);
        lu.appendChild(document.createElement("br"));
    }
}


function criarListaResultado(imagem, nome, result, src) {
    lu = document.getElementById("lista_resultado");

    lu.appendChild(document.createElement("hr"));
    li = document.createElement("li");
    img = document.createElement("img");
    img.setAttribute('src', imagem);
    img.setAttribute('title', nome);
    img1 = document.createElement("img");
    img1.setAttribute('src', src);
    img1.setAttribute('title', nome);
    item = document.createElement("p");
    item.innerHTML = nome;
    chance = document.createElement("p");
    chanceNome = "Chance de Acerto: ";
    chance.innerHTML = chanceNome + String(result) + "%";
    li.appendChild(img);
    li.appendChild(document.createElement("br"));
    li.appendChild(img1);
    li.appendChild(item);
    li.appendChild(chance);
    li.style.margin = "20px";
    lu.appendChild(li);

    lu.appendChild(document.createElement("hr"));
}

function validar(valor, check) {
    valor = document.getElementById(valor);
    check = document.getElementById(check);
    if (valor.value === check.value) {
        if (confirm("Tem certeza que deseja continuar?")) {
            return true;
        } else {
            return false;
        }
    } else {
        alert("As senhas inseridas são diferentes");
    }
    return false;
}

function criarResultadoInfo(nome, resultado, versao) {

    grupo = document.getElementById("info_resultado");
    linha = document.createElement("li");

    bold = document.createElement("b");
    bold.innerHTML = "Nome Padrão:" + nome;
    linha.appendChild(bold);
    linha.appendChild(document.createElement("br"));
    pResultado = document.createElement("p");
    pResultado.innerHTML = "Acerto da Análise: " + resultado + "%";
    linha.appendChild(pResultado);
    linha.appendChild(document.createElement("br"));
    pVersion = document.createElement("p");
    pVersion.innerHTML = "Algoritmo: V" + versao + "";
    linha.appendChild(pVersion);
    linha.appendChild(document.createElement("br"));



    grupo.appendChild(linha);



}


function checkboxSelected(id) {

    var element = document.getElementById(id);
    element.style.opacity = "0.9";
    element.style.filter = 'alpha(opacity=90)'; // IE fallback

}
