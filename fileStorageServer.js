/**
 * Created by korolev on 24.10.16.
 */

'use strict';

var fs = require('fs'),
    path = require('path'),
    eventHandler = require(__base + 'server-architecture/eventHandler');


class fileStorageServer  extends eventHandler{
    constructor(options) {
        super();
        console.log("my construct hit")
    }
    init() {
        this.trigger('init-complete', this);
    }

    registerNewClient(socket) {
        console.log('socket maintaince');
        socket.on("foo", function (data) {
            console.log("azzzzzzzz = " + data)
        });

        var path = "/Users/korolev/tmp/tstfile.txt";
        var data = "Hello from the Node writeFile method!";

        fs.writeFile(path, data, function(error) {
            if (error) {
                console.error("write error:  " + error.message);
            } else {
                console.log("Successful Write to " + path);
            }
        });
    }
}

module.exports = fileStorageServer;