const db = require('../models');
const Tutorial = db.tutorials;
const Op = db.Sequelize.Op;

exports.create = (req,res) =>{
    if(!req.body.title){
        res.status(400).send({message: 'Contenido no puede ser vacio'});
        return;
    }

    const tutorial = {
        title: req.body.title,
        description: req.body.description,
        published: req.body.published ? req.body.published : false
    };

    Tutorial.create(tutorial).then(data => {
        res.send(data);
    }).catcher(err => {
        res.status(500).send({message: err.message || 'Se produjo un error'});
    });
};

exports.findAll = (req,res) => {
    const title = req.body.title;
    var condition = title ? { title: {[Op.like]: `%${title}%` }} : null;
    
    Tutorial.findAll({where: condition}).then(data => {
        res.send(data);
    }).catcher(err => {
        res.status(500).send({message: err.message || 'Se produjo un error'});
    });
};
/*
https://github.com/bezkoder/nodejs-express-sequelize-mysql/blob/master/app/controllers/tutorial.controller.js

linea 53 findOne
*/