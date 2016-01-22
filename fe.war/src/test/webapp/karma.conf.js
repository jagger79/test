module.exports = function(config){
  config.set({

    basePath : '../../../',

    files : [
      'src/test/webapp/libs/bower_components/angular/angular.js',
      'src/test/webapp/libs/bower_components/angular-mocks/angular-mocks.js',
      'src/test/webapp/unit/*.js',
      'src/main/webapp/js/**/*.js'
    ],

    autoWatch : true,

    frameworks: ['jasmine'],

    browsers : ['Firefox'],

    plugins : [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-jasmine'
            ],

    junitReporter : {
      outputFile: 'src/test/webapp/test_out/unit.xml',
      suite: 'unit'
    }

  });
};