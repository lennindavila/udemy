
class Principal {
    userLink(URLactual) {
        let url = "";
        let cadena = URLactual.split("/");
        for (var i = 0; i < cadena.length; i++) {
            if (cadena[i] != "Index") {
                url += cadena[i];
            }
        }
        switch (url) {
            case "CursosCursos":
                
                document.getElementById('files').addEventListener('change', cursoImage, false);
                break;
        }
    }
}
