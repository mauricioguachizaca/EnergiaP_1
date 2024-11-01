from flask import Blueprint, abort, request, render_template, redirect
import json
import requests
from flask import flash
router = Blueprint('router', __name__)

@router.route('/')
def home():
    return render_template('template.html')

##Proyecto routes
@router.route('/listas/proyecto')
def listas_proyecto():
    r = requests.get('http://localhost:8099/api/proyecto/lista')
    data = r.json()
    return render_template('proyecto/proyectos.html', lista = data["data"])

## Guardar el proyecto 
@router.route('/proyecto/crear', methods=['POST'])
def crear_proyecto():
    hearders = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "nombre": form['nom'],
        "inversion": form['inver'],
        "tiempodevida": form['tiv'],
        "fechaInicio": form['fI'],
        "fechaFin": form['fF'],
        "electicidadGeneradapordia": form['egpd'],
        "acronimo": form['acr'],
        "costototal": form['ct'],
    }
    r = requests.post('http://localhost:8099/api/proyecto/guardar', headers=hearders, data=json.dumps(dataF))
    dat = r.json()
    if r.status_code == 200:
        flash("Se guardo correctamente", category='info')
        return redirect('/listas/proyecto')
    else:
        flash(str(dat["message"]), category='error')
        return redirect('/listas/proyecto')
    
##Vista del guardar proyecto
@router.route('/proyecto/formulario')
def formulario_proyecto():
    return render_template('proyecto/guardarproyecto.html')

##llamar para editar el proyecto
@router.route('/proyecto/editar/<id>')
def editar_proyecto(id):
    r = requests.get('http://localhost:8099/api/proyecto/lista/'+id)
    data = r.json()
    if(r.status_code == 200):
        return render_template('proyecto/editarproyecto.html', lista = data["data"])
    else:
        flash(data["data"],"Error al cargar la pagina", category='error')
        return redirect('/listas/proyecto')  

##Editar el proyecto 
@router.route('/proyecto/editar', methods=['POST'])
def editarproyecto():
    hearders = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "idProyecto": form['id'],	
        "nombre": form['nom'],
        "inversion": form['inver'],
        "tiempodevida": form['tiv'],
        "fechaInicio": form['fI'],
        "fechaFin": form['fF'],
        "electicidadGeneradapordia": form['egpd'],
        "acronimo": form['acr'],
        "costototal": form['ct'],
    }
    r = requests.post('http://localhost:8099/api/proyecto/editar', headers=hearders, data=json.dumps(dataF))
    dat = r.json()
    if r.status_code == 200:
        flash("Se editado correctamente", category='info')
        return redirect('/listas/proyecto')
    else:
        flash(str(dat["message"]), category='error')
        return redirect('/listas/proyecto')