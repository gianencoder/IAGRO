const tarjeta = document.querySelector('#tarjeta'),
    btnAbrirFormulario = document.querySelector('#btn-abrir-formulario'),
    formulario = document.querySelector('#formulario-tarjeta'),
    delantera = document.querySelector('#delantera'),
    loc = document.querySelector('#cmbLocalidad'),
    reg = document.querySelector('#cmbReg'),
    locality = document.querySelector('#locCbm'),
    label = document.querySelector('#delantera .tituloView'),
    numero = document.querySelector('#delantera .numero'),
    dlLocalidad = document.querySelector('#dlLocalidad'),
    locCbm = document.querySelector('.select'),
    depCbm = document.querySelector('.selectDep'),
    regCbm = document.querySelector('.selectReg'),
    estCbm = document.querySelector('.selectEst'),
    metCbm = document.querySelector('.selectMet'),
    eqpCbm = document.querySelector('.selectEqp'),
    inactive = document.querySelector('#inactive'),
    verificate = document.querySelector('.fa-certificate'),
    adLocalidad = document.querySelector('#adLocalidad'),
    adDepartamento = document.querySelector('#adDepartamento'),
    adRegion = document.querySelector('#adRegion'),
    adEstacion = document.querySelector('#adEstacion'),
    txtActive = document.querySelector('.ai'),
    aon = document.querySelector('#adActivo'),
    adEquipamiento = document.querySelector('#adEquipamiento'),
    adMetodo = document.querySelector('#adMetodo'),
    est = document.querySelector('#cmbEst'),
    obligatorio = document.querySelector('#isObl'),
    txtNombre = document.querySelector('#txtCampo'),
    tipoDato = document.querySelector('#cmbTipoDato'),
    eqp = document.querySelector('#cmbEqp'),
    met = document.querySelector('#cmbMet'),
    dep = document.querySelector('#cmbDep'),
    flexbox = document.querySelector("#flexbox"),
    deleteDiv = document.querySelector("#deleteDiv")

//Funciones para localidad
const btnAdd = document.querySelector("#adLocalidad");
btnAdd.addEventListener("click", AddNew);

function AddNew() {

    btnAdd.classList.toggle('active');
    loc.classList.toggle('active');

    if (adLocalidad.checked == true) {
        locCbm.value = "SI";
    } else {
        locCbm.value = "NO";

    }

}

//Funciones para departamento
const btnAddD = document.querySelector("#adDepartamento");
btnAddD.addEventListener("click", depEvent);

function depEvent() {
    btnAddD.classList.toggle('active');
    dep.classList.toggle('active');
    if (adDepartamento.checked == true) {
        depCbm.value = "SI";
    } else {
        depCbm.value = "NO";
    }
}
//Funciones para Region
const addReg = document.querySelector("#adRegion");
addReg.addEventListener("click", regEvent);

function regEvent() {
    addReg.classList.toggle('active');
    reg.classList.toggle('active');

    if (adRegion.checked == true) {
        regCbm.value = "SI";
    } else {
        regCbm.value = "NO";
    }

}

//Funciones para Estacion
const addEst = document.querySelector("#adEstacion");
addEst.addEventListener("click", estEvent);

function estEvent() {
    addEst.classList.toggle('active');
    est.classList.toggle('active');

    if (adEstacion.checked == true) {
        estCbm.value = "SI";
    } else {
        estCbm.value = "NO";
    }


}

//Funciones para Metodo
const addMet = document.querySelector("#adMetodo");
addMet.addEventListener("click", metEvent);

function metEvent() {
    addMet.classList.toggle('active');
    met.classList.toggle('active');

    if (adMetodo.checked == true) {
        metCbm.value = "SI";
    } else {
        metCbm.value = "NO";
    }

}

//Funciones para Equipamiento
const addEqp = document.querySelector("#adEquipamiento");
addEqp.addEventListener("click", eqpMet);

function eqpMet() {
    addEqp.classList.toggle('active');
    eqp.classList.toggle('active');
    if (adEquipamiento.checked == true) {
        eqpCbm.value = "SI";
    } else {
        eqpCbm.value = "NO";
    }
}

const activar = document.querySelector('#adActivo');
activar.addEventListener("click", act);

function act() {

    activar.classList.toggle('active');

    if (aon.checked == true) {
        verificate.classList.add('verde');
        verificate.classList.remove('rojo');
        txtActive.value = "Activo";
        inactive.innerHTML = "(Activo)";


    } else {

        verificate.classList.remove('verde');
        verificate.classList.toggle('rojo');
        txtActive.value = "Inactivo"
        inactive.innerHTML = "(Inactivo)"

    }

}

//Limpiar campos
const btnClean = document.querySelector("#clean");
btnClean.addEventListener("click", clean);

function clean() {
    obligatorio.checked = false;
    txtNombre.value = "";
}


formulario.inputNumero.addEventListener('keyup', (e) => {

    let valorInput = e.target.value;
    formulario.inputNumero.value = valorInput
        .trim();
    label.textContent = valorInput;
});

formulario.inputNombre.addEventListener('keyup', (e) => {

    let valorInput = e.target.value;
    formulario.inputNombre.value = valorInput
    numero.textContent = valorInput;
});