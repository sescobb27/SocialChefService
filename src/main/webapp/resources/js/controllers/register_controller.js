SocialChef.RegisterController = Ember.ObjectController.extend({
    // ==========================================================================
    // PROPERTIES
    // ==========================================================================
    name: '',
    last_name: '',
    u_username: '',
    email: '',
    u_password: '',
    confirm_password: '',
    address: '',
    cellphone: '',
    telephone: '',
    isProcessing: false,
    errors: Ember.A([]),
    // ==========================================================================
    // END PROPERTIES
    // ==========================================================================

    actions: {
        validate: function () {
            this.set('isProcessing', true);
            var name = this.get("name");
            var last_name = this.get("last_name");
            var username = this.get("u_username");
            var email = this.get('email');
            var pass = this.get('u_password');
            var confirm = this.get('confirm_password');
            var address = this.get('address');
            var cellphone = this.get('cellphone');
            var telephone = this.get('telephone');
            var errors = this.get('errors');
            errors.clear();
            if (!this.empty(pass) && !this.empty(confirm)) {
                if (pass !== confirm) {
                    errors.pushObject
                        ("La contrase\u00F1a y la confirmaci\u00F3n de la contrase\u00F1a no concuerdan");
                }
            } else {
                errors.pushObject
                    ("La contrase\u00F1a y la confirmaci\u00F3n de la contrase\u00F1a no pueden estar vacias");
            }

            if (this.empty(name)) {
                errors.pushObject("El Nombre No puede estar vacio");
            }

            if (this.empty(last_name)) {
                errors.pushObject("El Campo Apellido No puede estar vacio");
            }

            if (this.empty(username)) {
                errors.pushObject("El Campo Usuario No puede estar vacio");
            }

            if (this.empty(email)) {
                errors.pushObject("El Campo Correo No puede estar vacio");
            }

            if (this.empty(address)) {
                errors.pushObject("El Campo Direcci\u00F3n No puede estar vacio");
            }
            if (this.empty(cellphone)) {
                errors.pushObject("El Campo Celular No puede estar vacio");
            }
            if (this.empty(telephone)) {
                errors.pushObject("El Campo Telefono No puede estar vacio");
            }

            if (errors.length !== 0) {
                this.set('isProcessing', false);
                return false;
            } else {
                this.send('register');
            }
        },

        register: function() {
            var self = this;
            var promise = Ember.$.ajax({
                type: 'POST',
                url: "/chefs",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(self.serialize())
            });
            promise.success(function(){
                Ember.run(function(){
                    self.success();
                });
            });
            promise.fail(function(response){
                Ember.run(function(){
                    self.failure(response);
                });
             });
        }
    },
    empty: function(obj) {
        return obj === "" || obj === null;
    },
    serialize: function() {
        return {
            name: this.get('name'),
            lastName: this.get('last_name'),
            userName: this.get('u_username'),
            email: this.get('email'),
            password: this.get('u_password')
        };
    },
    success: function() {
        console.log("===SUCCESS===");
        var controller = SocialChef.__container__.lookup("controller:application");
        controller.send('loggedIn', this.get('u_username'));
        this.transitionToRoute('user', this.get('u_username'));
        // this.reset();
    },
    failure: function(response) {
        this.set('isProcessing', false);
        this.get('errors').pushObject(response.errors);
    }
});
