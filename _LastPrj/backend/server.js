const express = require('express');
const logger = require('morgan');
const products = require('./app/api/routes/products');
const users = require('./app/api/routes/users');
const bodyParser = require('body-parser');
const mongoose = require('./config/database');
var jwt = require('jsonwebtoken');
const app = express();

app.set('secretKey', 'reallyhardkey');

mongoose.connection.on('error', console.error.bind(console, 'MongoDB connection error:'));

app.use(logger('dev'));
app.use(bodyParser.json());

app.use((req, res, next) => {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

app.get('/', (req, res) => {
    res.json({"author": "Brandon SIMON-VERMOT & Quentin ARCICAULT"});
});

app.use('/users', users);

app.use('/products', products);

app.get('/favicon.ico', (req, res) => {
    res.sendStatus(204);
});

app.use((req, res, next) => {
    let err = new Error('Not Found');
    err.status = 404;
    next(err);
});

app.use((err, req, res, next) => {
    console.log(err);
    if (err.status === 404) {
        res.status(404).json({message: "Not Found"});
    } else {
        res.status(500).json({message: "Something looks wrong :("});
    }
});

app.listen(3000, () => {
    console.log('Node server listening on port 3000');
});
