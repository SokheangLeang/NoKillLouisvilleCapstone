const mongoose = require("mongoose");
const Figures = require("./appointment");
const Appointment = require("./appointment");

module.exports = {
    addAppointment: (req, res) => {
        try{
            let appointment = new Appointment({
                name: req.params.name,
                appointmentTime: req.params.time,
                appointmentDate: req.params.date
            });
            appointment.save().then(() => res.status(200).send('Successfully added appointment for ' + appointment.name));
        }
        catch(error){
            res.status(400).send('Error');
            console.log(error);
        }

    },

    getAll: (req, res) => {
        Appointment.find().select({_id: 0, __v: 0}).lean().exec(function(err, appointments){
            res.status(200).end(JSON.stringify(appointments));
        });
    },
    getAppointmentByName: (req, res) => {
        Appointment.find({name: req.params.name}).select({_id: 0, __v: 0}).lean().exec(function(err, names){
            res.status(200).end(JSON.stringify(names));
        });
    },
    getAppointmentByDate: (req, res) => {
        Figures.find({appointmentDate: req.params.date}).select({_id: 0, __v: 0}).lean().exec(function(err, date){
            res.status(200).end(JSON.stringify(date));
        });
    },

    updateAppointmentTime: (req, res) => {
        try{
            Figures.findOneAndUpdate( {name: req.params.name, appointmentDate: req.params.date}, {appointmentTime: req.params.time}, function(err, updated){
                res.status(200).send(req.params.name + '\'s time updated.')
            });
        }
        catch(error){
            res.status(400);
            console.log(error);
        }
    },

    confirmAppointment: (req, res) => {
        try{
            Figures.findOneAndUpdate( {name: req.params.name, appointmentDate: req.params.date}, {confirmationByVolunteer: true}, function(err, updated){
                res.status(200).send(req.params.name + ' appointment confirmed.')
            });
        }
        catch(error){
            res.status(400);
            console.log(error);
        }
    },

    cancelAppointment: (req, res) => {
        try{
            Figures.findOneAndUpdate( {name: req.params.name, appointmentDate: req.params.date}, {cancelled: true}, function(err, updated){
                res.status(200).send(req.params.name + ' appointment cancelled.')
            });
        }
        catch(error){
            res.status(400);
            console.log(error);
        }
    }
}