SocialChef.RegisterController = Ember.ObjectController.extend({
    // ==========================================================================
    // PROPERTIES
    // ==========================================================================
    name: '',
    last_name: '',
    username: '',
    email: '',
    password: '',
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
            var name = $.trim(this.get("name"));
            var last_name = $.trim(this.get("last_name"));
            var username = $.trim(this.get("username"));
            var email = $.trim(this.get('email'));
            var pass = $.trim(this.get('password'));
            var confirm = $.trim(this.get('confirm_password'));
            var address = $.trim(this.get('address'));
            var cellphone = $.trim(this.get('cellphone'));
            var telephone = $.trim(this.get('telephone'));
            var errors = this.get('errors');
            errors.clear();
            if (!this.empty(pass) && !this.empty(confirm)) {
                if (pass !== confirm) {
                    errors.pushObject
                        ("La contraseña y la confirmación de la contraseña no concuerdan");
                }
            } else {
                errors.pushObject
                    ("La contraseña y la confirmación de la contraseña no pueden estar vacias");
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
                errors.pushObject("El Campo Dirección No puede estar vacio");
            }
            if (this.empty(cellphone)) {
                errors.pushObject("El Campo Celular No puede estar vacio");
            }
            if (this.empty(telephone)) {
                errors.pushObject("El Campo Telefono No puede estar vacio");
            }

            if (this.get('errors').length !== 0) {
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
            userName: this.get('username'),
            email: this.get('email'),
            password: this.get('password')
        };
    },
    success: function() {
        var controller = SocialChef.__container__.lookup("controller:application");
        controller.send('loggedIn', this.get('username'));
        this.transitionToRoute('user', this.get('username'));
        this.reset();
    },
    failure: function(response) {
        this.set('isProcessing', false);
        this.get('errors').pushObject(response.errors);
    }
});
