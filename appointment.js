const mongoose = require('mongoose');
const { Schema } = mongoose;

const appointmentSchema = new Schema({
    name: {
        type: String,
        required: true
    },
    appointmentDate: {
        type: String,
        required: true
    },
    appointmentTime: {
        type: String,
        required: true
    },
    confirmationByVolunteer: {
        type: Boolean,
        required: true,
        default: false
    },
    cancelled: {
        type: Boolean,
        required: true,
        default: false
    }
});

const Appointment = mongoose.model('Appointment', appointmentSchema);
module.exports = Appointment;