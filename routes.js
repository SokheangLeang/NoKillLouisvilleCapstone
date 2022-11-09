let express = require('express');
let controller = require('./controllers');
let routes = express.Router();

routes.route('/v1/appointment/add/:name/:date/:time')
    .post(controller.addAppointment);




routes.route('/v1/appointment/update/:name/:date/:time')
    .put(controller.updateAppointmentTime);
routes.route('/v1/appointment/confirm/:name/:date')
    .put(controller.confirmAppointment);
routes.route('/v1/appointment/cancel/:name/:date')
    .put(controller.cancelAppointment);

routes.route('/v1/appointment')
    .get(controller.getAll);
routes.route('/v1/appointment/:name')
    .get(controller.getAppointmentByName);
routes.route('/v1/appointment/date/:date')
    .get(controller.getAppointmentByDate);

module.exports = routes;