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
    


## inversionista routes
## Obtener la lista de inversionistas relacionados con un proyecto

@router.route('/inversionista/listas/<acronimo>')
def lista_inversionistas(acronimo):
    r = requests.get(f'http://localhost:8099/api/inversionista/listas/{acronimo}')
    data = r.json()
    return render_template('inversionista/inversionistas.html', lista=data["data"])

@router.route('/inversionista/formulario/<acronimo>')
def formulario_asociar_inversionista(acronimo):
    r = requests.get(f'http://localhost:8099/api/inversionista/listas/{acronimo}')
    data = r.json()
    return render_template('inversionista/guardarinversionista.html', lista=data["data"])



@router.route('/inversionista/<acronimo>')
def inversionistas(acronimo):
    r = requests.get(f'http://localhost:8099/api/inversionista/listas/{acronimo}')
    data = r.json()
    
    if r.status_code == 200:
        return render_template('templateinversion.html', lista=data["data"])
    else:
        flash(data["msg"], category='error')
        return redirect('/listas/proyecto')




## Asociar un inversionista a un proyecto
@router.route('/inversionista/asociar/<acronimo>', methods=['POST'])
def asociar_inversionista(acronimo):
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {
        "nombre": form['nombre'],
        "apellido": form['apellido'],
        "dni": form['dni'],
        "telefono": form['telefono'],
    }
    
    r = requests.post(f'http://localhost:8099/api/inversionista/asociar/{acronimo}', headers=headers, data=json.dumps(dataF))
    dat = r.json()
    
    if r.status_code == 200:
        flash("Inversionista asociado correctamente", category='info')
        return redirect(f'/inversionista/listas/{acronimo}')
    else:
        flash(str(dat["msg"]), category='error')
        return redirect(f'/inversionista/listas/{acronimo}')

## Formulario para asociar inversionista a proyecto
