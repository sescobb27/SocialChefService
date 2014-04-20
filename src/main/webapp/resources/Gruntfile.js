module.exports = function(grunt) {
    grunt.initConfig({
        pkg: grunt.file.readJSON( 'package.json' ),

        emberTemplates: {
            compile: {
                options: {
                    templateBasePath: /js\/templates\//
                },
                files: {
                    'js/templates/components/templates.js': ['js/templates/components/*.hbs',
                        'js/templates/*.hbs',
                        'js/templates/**/*.hbs']
                }
            }
        },

        watch: {
            emberTemplates: {
                files: ['js/templates/components/*.hbs',
                            'js/templates/*.hbs',
                            'js/templates/**/*.hbs'],
                tasks: ['emberTemplates']
            }
        }
    });
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-ember-templates');

    grunt.registerTask('default', ['emberTemplates', 'watch']);
};
