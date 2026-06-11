var iconePerfil = document.querySelector(".btnLogin")
var telaBloqueio = document.querySelector(".escurecido");
var telaFormularioLogin = document.querySelector("#telaLogin")
var botaoCancelar = document.querySelector("#btnCancelar")


botaoCancelar.addEventListener('click', Cancelar)
iconePerfil.addEventListener('click', SurgirLogin)


function Cancelar(event){
    event.preventDefault();
    telaBloqueio.classList.add("escondido");
    telaFormularioLogin.classList.add("escondido");
}

function SurgirLogin(event){
    event.preventDefault();
    telaBloqueio.classList.remove("escondido");
    telaFormularioLogin.classList.remove("escondido");

}

function SurgirCadastro(event){
    event.preventDefault();
    telaBloqueio.classList.remove("escondido");
    telaFormularioLogin.classList.add("escondido");
}