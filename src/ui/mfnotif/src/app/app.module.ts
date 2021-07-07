import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { SpinnerComponent } from './spinner/spinner.component';
import { LoginComponent } from './login/login.component';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {Compiler, COMPILER_OPTIONS, CompilerFactory} from '@angular/core';
import {JitCompilerFactory} from '@angular/platform-browser-dynamic';
import { HeaderComponent } from './header/header.component';
export function createCompiler(compilerFactory: CompilerFactory) {
  return compilerFactory.createCompiler();
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SpinnerComponent,
    LoginComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{provide: COMPILER_OPTIONS, useValue: {}, multi: true},
                  {provide: CompilerFactory, useClass: JitCompilerFactory, deps: [COMPILER_OPTIONS]},
                  {provide: Compiler, useFactory: createCompiler, deps: [CompilerFactory]}],
  bootstrap: [AppComponent]
})
export class AppModule { }
