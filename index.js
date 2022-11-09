let express = require('express');
let bodyParser = require('body-parser');
let routes = require('./routes');
let path = require('path');
const mongoose = require("mongoose");



let port = 3000;
let app = express();
mongoose.connect('mongodb://127.0.0.1:27017/appointment', {useNewUrlParser: true, useUnifiedTopology: true});
app.use(bodyParser.json());
app.use('/', routes);

app.listen(port, () => console.log('http://localhost:%d', port));
