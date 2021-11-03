﻿using SistemasWeb.Areas.Cursos.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace SistemasWeb.Areas.Categorias.Models
{
    public class TCategoria
    {
        [Key]
        public int CategoriaID { get; set; }
        [Required(ErrorMessage = "El campo categoria es obligatorio.")]
        public string Categoria { get; set; }
        [Required(ErrorMessage = "El campo descripcion es obligatorio.")]
        public string Descripcion { get; set; }
        public Boolean Estado { get; set; } = true;
        public ICollection<TCursos> Cursos { get; set; }
    }
}
