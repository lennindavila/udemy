'use strict'

var mongoose = require('./node_modules/mongoose');
let app = require('./app');
let port = process.env.PORT || 3678;


mongoose.connect('mongodb://localhost:27017/cursofavoritos',(err,res) => {

    if(err){
        throw err;
    }else{
        console.log('Conexion a MongoDB correcta');
        app.listen(port,function(){
            console.log(`API REST FAVORITOS funcionando en http://localhost:${port}`);
        });
    }
});



