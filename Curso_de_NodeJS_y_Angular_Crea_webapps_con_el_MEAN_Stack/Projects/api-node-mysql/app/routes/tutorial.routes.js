module.exports = app => {
    const tutorials = require('../controllers/tutorial.controller');

    var router = require('express').Router();

    //Crear new Tutorial
    router.get('/',tutorials.findAll);
    router.get('/',tutorials.findAllPublished);
    router.get('/',tutorials.findOne);    
    router.post('/',tutorials.create);
    router.put('/:id',tutorials.update);
    router.delete('/:id',tutorials.delete);
    router.delete('/',tutorials.deleteAll);
    app.use('/api/tutorials',router);
};