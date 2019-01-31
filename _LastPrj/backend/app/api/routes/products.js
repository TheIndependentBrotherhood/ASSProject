const express = require('express');
const router = express.Router();
const productController = require('../controllers/products');
const jwt = require('jsonwebtoken');

router.get('/', productController.getAll);
router.post('/', isAdmin, productController.create);
router.get('/:movieId', productController.getById);
router.put('/:movieId', isAdmin, productController.updateById);
router.delete('/:movieId', isAdmin, productController.deleteById);

function isAdmin(req, res, next) {
    jwt.verify(req.headers['authorization'].replace('Bearer ', ''), req.app.get('secretKey'), (err, decoded) => {
        if (err) {
            res.json({status: "error", message: err.message, data: null});
        } else {
            if (decoded.role === "admin") {
                next();
            } else {
                res.json({status: "error", message: "You must be administrator to access to this content.", data: null});
            }
        }
    })
}

module.exports = router;