const mongoose = require('mongoose');
const User = require('../app/api/models/users');

const chai = require('chai');
const chaiHttp = require('chai-http');
const server = require('../server');
const should = chai.should;

chai.use(chaiHttp);

describe('Users', () => {
    beforeEach(done => {
        User.remove({}, err => {
            done();
        });
    });
});

describe('/GET users/:userId', () => {
    it('should GET one user', done => {
        let user = new User({
            username: 'test',
            email: 'test@ŧest.fr',
            password: 'password',
            fullname: 'test TEST',
            address: '1 rue du TEST'
        });
        chai.request(server)
            .get('/users/' + user.id)
            .end((err, res) => {
                res.should.have.status(200);
            });
    });
});