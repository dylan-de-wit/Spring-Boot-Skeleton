import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthModule } from "@auth0/auth0-angular";
import { environment } from "../environments/environment";
import { LoginButtonComponent } from './login-button/login-button.component';
import { UserProfileComponent } from './user-profile/user-profile-component.component';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthHttpInterceptor } from '@auth0/auth0-angular';
import { UserMetadataComponent } from './user-metadata/user-metadata.component';
import { UserListComponent } from './user-list/user-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginButtonComponent,
    UserProfileComponent,
    UserMetadataComponent,
    UserListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,

    AuthModule.forRoot({
      domain: environment.auth0Domain,
      clientId: environment.clientId,
      audience: environment.auth0Audience,
      scope: environment.loginScope,

      httpInterceptor: {
        allowedList: [
          {
            // Match any request that starts 'https://skeleton.eu.auth0.com/api/v2/' (note the asterisk)
            uri: `${environment.auth0Audience}*`,
            tokenOptions: {
              // The attached token should target this audience
              audience: environment.auth0Audience,

              // The attached token should have these scopes
              scope: environment.loginScope
            }
          },
          {
            uri: `${environment.apiUri}/*`,
            tokenOptions: {
              audience: environment.auth0Audience,
            }
          },
        ]
      }
    })
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthHttpInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
