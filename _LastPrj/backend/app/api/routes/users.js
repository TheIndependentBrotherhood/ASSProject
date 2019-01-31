const express = require('express');
const router = express.Router();
const userController = require('../controllers/users');
const jwt = require('jsonwebtoken');

router.post('/register', userController.create);
router.post('/authenticate', userController.authenticate);
router.get('/:userId', isAccountOwner, userController.getById);
router.put('/:userId', isAccountOwner, userController.updateById);
router.delete('/:userId', isAccountOwner, userController.deleteById);

function isAccountOwner(req, res, next) {
    jwt.verify(req.headers['authorization'].replace('Bearer ', ''), req.app.get('secretKey'), (err, decoded) => {
        if (err) {
            res.json({status: "error", message: err.message, data: null});
        } else {
            if (decoded.id === req.params.userId || decoded.role === "admin") {
                next();
            } else {
                res.json({status: "error", message: "This account is not yours !", data: null});
            }
        }
    });
}

module.exports = router;