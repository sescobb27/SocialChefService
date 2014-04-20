SocialChef.UploadFile = Ember.TextField.extend({
    tagName: 'input',
    attributeBindings: ['name'],
    type: 'file',
    file: null,
    change: function (event) {
        var reader = new FileReader(),
                context = this;
        this.set('file', event.target.files[0]);
        reader.onload = function (event) {
            var image = event.target.result;
            Ember.run(function() {
                $('#image_thumbnail').attr('src', image);
            });
        };
        return reader.readAsDataURL(event.target.files[0]);
    }
});