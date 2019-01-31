const productModel = require('../models/products');

module.exports = {
    getById: function(req, res, next) {
        productModel.findById(req.params.productId, 
            (err, productInfo) => {
                if (err) {
                    next(err);
                } else {
                    res.json({status: "success", message: "Product found !", data: {products: productInfo}});
                }
            })
    },

    getAll: function(req, res, next) {
        let productsList = [];

        productModel.find({}, (err, products) => {
            if (err) {
                next(err);
            } else {
                for (let product of products) {
                    productsList.push({id: product._id,name: product.name,description: product.description,price: product.price,photo_url: product.photo_url});
                }

                res.json({status: "success", message: "Products list found !", data: {products: productsList}});
            }
        });
    },

    updateById: function(req, res, next) {
        productModel.findByIdAndUpdate(req.params.productId,{name:req.body.name, description: req.body.description, price: req.body.price, photo_url: req.body.photo_url}, (err, productInfo) => {
            if(err)
                next(err);
            else
                res.json({status:"success", message: "Product updated successfully !", data:null});
        });
    },

    deleteById: function(req, res, next) {
        productModel.findByIdAndRemove(req.params.productId, function(err, movieInfo) {
            if(err)
                next(err);
            else 
                res.json({status:"success", message: "Product deleted successfully !", data:null});
        });
    },

    create: function(req, res, next) {
        productModel.create({ name: req.body.name, description: req.body.description, price: req.body.price, photo_url: req.body.photo_url}, function (err, result) {
            if (err) 
                next(err);
            else
                res.json({status: "success", message: "Product added successfully !", data: null});
        });
    }
}