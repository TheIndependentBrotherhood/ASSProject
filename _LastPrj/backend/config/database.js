const mongoose = require('mongoose');
const mongoURI = 'mongodb://localhost/ecommerce';
mongoose.connect(mongoURI, {useNewUrlParser: true});
mongoose.Promise = global.Promise;

module.exports = mongoose;