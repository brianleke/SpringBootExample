describe('Route Provider', function () {
    var routes;

    beforeEach(function () {
        module('springAngularApp');

        inject(function ($route) {
            routes = $route;

        });
    });

    it('should have a home route', function () {
        expect((Object.keys(routes.routes))).toContain('/');
        expect(routes.routes['/'].controller).toBe('HomeController');
        expect(routes.routes['/'].templateUrl).toBe('index.html');
    });
});