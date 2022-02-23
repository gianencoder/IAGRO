
const departamento = document.querySelector('.cmbDep'),
    localidad = document.querySelector('.cmbLocalidad'),
    region = document.querySelector('.cmbReg'),
    estacion = document.querySelector('.cmbEst'),
    metodo = document.querySelector('.cmbMet'),
    equipamiento = document.querySelector('.cmbEqp'),
    verificate = document.querySelector('.fa-certificate'),
    inactive = document.querySelector('.inactive')


var a = $("#lblActivo").val();
var d = $("#lblDepartamento").val();
var l = $("#lblLocalidad").val();
var r = $("#lblRegion").val();
var o = $("#lblEstacion").val();
var m = $("#lblMetodo").val();
var e = $("#lblEquipamiento").val();


if (d == "NO") {
    departamento.classList.remove("active");
}

if (l == "NO") {
    localidad.classList.remove("active");
}
if (r == "NO") {
    region.classList.remove("active");
}

if (o == "NO") {
    estacion.classList.remove("active");
}

if (m == "NO") {
    metodo.classList.remove("active");
}

if (e == "NO") {
    equipamiento.classList.remove("active");
}

if (a == "Activo") {
    verificate.classList.add('verde');
    verificate.classList.remove('rojo');
    inactive.innerHTML= "(Activo)";
}

if (a == "Inactivo") {
    verificate.classList.remove('verde');
    verificate.classList.add('rojo');
    inactive.innerHTML= "(Inactivo)"
}