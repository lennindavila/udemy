﻿using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.DependencyInjection;
using SistemasWeb.Areas.Cursos.Models;
using SistemasWeb.Data;
using SistemasWeb.Library;
using SistemasWeb.Models;

namespace SistemasWeb.Controllers
{
    public class HomeController : Controller
    {
        private LCursos _curso;
        private static DataPaginador<TCursos> models;
        private static DataCurso _dataCurso;
        private SignInManager<IdentityUser> _signInManager;
        private UserManager<IdentityUser> _userManager;
        private static IdentityError identityError;
        //IServiceProvider _serviceProvider;
        public HomeController(ApplicationDbContext context, UserManager<IdentityUser> userManager, 
            SignInManager<IdentityUser> signInManager, IServiceProvider serviceProvider)
        {
            //_serviceProvider = serviceProvider;
            _signInManager = signInManager;
            _userManager = userManager;
            _curso = new LCursos(context, null);
        }
        public IActionResult Index(int id, String filtrar)
        {
            Object[] objects = new Object[3];
            var data = _curso.getTCursos(filtrar);
            if (0 < data.Count)
            {
                var url = Request.Scheme + "://" + Request.Host.Value;
                objects = new LPaginador<TCursos>().paginador(data, id, 10, "", "Home", "Index", url);
            }
            else
            {
                objects[0] = "No hay datos que mostrar";
                objects[1] = "No hay datos que mostrar";
                objects[2] = new List<TCursos>();
            }
            models = new DataPaginador<TCursos>
            {
                List = (List<TCursos>)objects[2],
                Pagi_info = (String)objects[0],
                Pagi_navegacion = (String)objects[1],
                Input = new TCursos(),
            };
            if (identityError != null)
            {
                models.Pagi_info = identityError.Description;
                identityError = null;
            }
            //await CreateRolesAsync(_serviceProvider);
            return View(models);
        }
        public IActionResult Detalles(int id)
        {
            var model = _curso.getTCurso(id);
            return View(model);
        }
        [HttpPost]
        public async Task<IActionResult> Obtener(int cursoID, int vista)
        {
            if (_signInManager.IsSignedIn(User))
            {
                var user = await _userManager.GetUserAsync(User);
                var idUser = await _userManager.GetUserIdAsync(user);
                var data = _curso.Inscripcion(idUser, cursoID);
                if (data.Description.Equals("Done"))
                {
                    return Redirect("/Inscripciones/Inscripciones?area=Inscripciones");
                }
                else
                {
                    identityError = data;
                    if (vista.Equals(1))
                    {
                        return Redirect("/Home/Index");
                    }
                    else
                    {
                        _dataCurso = _curso.getTCurso(cursoID);
                        _dataCurso.ErrorMessage = data.Description;
                        return View("Detalles", _dataCurso);
                    }
                }
            }
            else
            {
                return Redirect("/Identity/Account/Login");
            }
               
        }
        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
        private async Task CreateRolesAsync(IServiceProvider serviceProvider)
        {
            var roleManager = serviceProvider.GetRequiredService<RoleManager<IdentityRole>>();
            var userManager = serviceProvider.GetRequiredService<UserManager<IdentityUser>>();
            String[] rolesName = { "Admin", "Student" };
            foreach (var item in rolesName)
            {
                var roleExist = await roleManager.RoleExistsAsync(item);
                if (!roleExist)
                {
                    await roleManager.CreateAsync(new IdentityRole(item));
                }
            }
            var user = await userManager.FindByIdAsync("b72ab1f0-6d72-496c-b971-256e715e5684");
            await userManager.AddToRoleAsync(user, "Admin");
        }
    }
}
