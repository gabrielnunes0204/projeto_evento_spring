function validarCamposEvento() {
  const botao = document.querySelector("#botao_evento");
  const campos = document.querySelectorAll(".campos_evento");
  const arrayCampos = Array.from(campos);

  if (botao != null) {
    botao.addEventListener("click", (event) => {

      if (arrayCampos[0].value == "") {
        alert("Preencha o campo do nome corretamente");
        event.preventDefault();
      } else if (arrayCampos[1].value == "") {
        alert("Preencha o campo do local corretamente");
        event.preventDefault();
      } else if (arrayCampos[2].value == "") {
        alert("Preencha o campo da data corretamente");
        event.preventDefault();
      } else if (arrayCampos[3].value == "") {
        alert("Preencha o campo do horário corretamente");
        event.preventDefault();
      } else {
        console.log("Foi");
      }
    });
  }
}
validarCamposEvento();

function validarCamposParticipante() {
  const botao = document.querySelector("#botao_participante");
  const campos = document.querySelectorAll(".campos_participante");
  const arrayCampos = Array.from(campos);

  if (botao != null) {
    botao.addEventListener("click", (event) => {

      if (arrayCampos[0].value == "") {
        alert("Preencha o campo do nome corretamente");
        event.preventDefault();
      } else if (arrayCampos[1].value == "") {
        alert("Preencha o campo do CPF corretamente");
        event.preventDefault();
      } else if (arrayCampos[2].value == "") {
        alert("Preencha o campo da data corretamente");
        event.preventDefault();
      } else {
        console.log("Foi");
      }
    });
  }
}
validarCamposParticipante();