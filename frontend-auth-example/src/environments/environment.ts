export const environment = {
  production: false,
  apiUri: 'http://localhost:8080/api',
  auth0Domain: 'skeleton.eu.auth0.com',
  clientId: 'iJvV0ISZzxykqoQlqcWEBerdAEa7ojYb',
  auth0Audience: 'https://skeleton.eu.auth0.com/api/v2/',
  loginScope: 'read:current_user'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
import 'zone.js/dist/zone-error';  // Included with Angular CLI.
