const userModel = require('../models/users');
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

module.exports = {
    create: function(req, res, next) {
        userModel.create({
            username: req.body.username,
            email: req.body.email,
            password: bcrypt.hashSync(req.body.password, 10),
            fullname: req.body.fullname,
            address: req.body.address,
            role: "user"
        }, (err, result) => {
            if (err)
                next(err);
            else
                res.json({
                    status: "success",
                    message: "User added successfully!",
                    data: null
                });
        });
    },

    getById: function(req, res, next) {
        userModel.findById(req.params.userId)
        .populate('orders')
        .exec((err, userInfo) => {
            if (err) {
                next(err);
            } else {
                res.json({status: "success", message: "User found !", data: userInfo});
            }
        });
    },

    updateById: function(req, res, next) {
        userModel.findByIdAndUpdate(req.params.userId, {
            username: req.body.username,
            password: bcrypt.hashSync(req.body.password, 10),
            email: req.body.email,
            fullname: req.body.fullname,
            address: req.body.address
        }, (err, userInfo) => {
            if (err)
                next(err);
            else
                res.json({status:"success", message: "User updated successfully !", data:null});
        })
    },

    deleteById: function(req, res, next) {
        userModel.findByIdAndRemove(req.params.userId, (err, userInfo) => {
            if (err)
                next(err);
            else
                res.json({status: "success", message: "User deleted successfully !", data: null});
        });
    },

    authenticate: function(req, res, next) {
        
        userModel.findOne({
            email: req.body.email
        }, (err, userInfo) => {
            if (err) {
                next(err);
            } else {
                if (bcrypt.compareSync(req.body.password, userInfo.password)) {
                    const token = jwt.sign({id: userInfo._id, role: userInfo.role}, req.app.get('secretKey'), {expiresIn: '1h'});
                    res.json({status: "success", message: "user found !", data: {user: userInfo, token: token}});
                } else {
                    res.json({status: "error", message: "Invalid email/password!", data: null})
                }
            }
        });
    },
}