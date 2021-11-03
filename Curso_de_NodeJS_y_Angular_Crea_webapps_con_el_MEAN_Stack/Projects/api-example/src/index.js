const express = require('express');
const app = express();
const path = require('path');

//setting
app.set('port',3000);
app.set('views',path.join(__dirname,'views/index.html'));
app.set('view engine','ejs');
//middlewares

//routes
app.get('/',(req,res) => {
    res.render('index',{title:'First web site'})
});

//static files

//listening the server
app.listen(app.get('port'), () => {
    console.log('Server on port',app.get('port'));
});
