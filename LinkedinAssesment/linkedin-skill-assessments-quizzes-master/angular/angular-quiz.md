## Angular

#### Q1. What is the purpose of the ViewChild decorator in this component class?

```javascript
@Component({
    ...
    template: '<p #bio></p>'
})
export class UserDetailsComponent {
    @ViewChild('bio') bio;
}
```

- [x] It provides access from within the component class to the ElementRef object for the `<p>` tag that has the bio template reference variable in the component's template view.
- [ ] It indicates that the `<p>` tag be rendered as a child of the parent view that uses this component.
- [ ] It makes the `<p>` tag in the template support content projection.
- [ ] It makes the `<p>` tag visible in the final render. If the #bio was used in the template and the @ViewChild was not used in the class, then Angular would automatically hide the `<p>` tag that has #bio on it.

#### Q2. What method is used to wire up a FormControl to a native DOM input element in reactive forms?

- [ ] Add the string name given to the FormControl to an attribute named controls on the <form> element to indicate what fields it should include.
- [ ] Use the square bracket binding syntax around the value attribute on the DOM element and set that equal to an instance of the FormControl.
- [x] Use the formControlName directive and set the value equal to the string name given to the FormControl.
- [ ] Use the string name given to the FormControl as the value for the DOM element id attribute.

#### Q3. What is the difference between the `paramMap` and the `queryParamMap` on the `ActivatedRoute` class?

- [ ] The paramMap is an object literal of the parameters in a route's URL path. The queryParamMap is an Observable of those same parameters.
- [ ] The paramMap is an Observable that contains the parameter values that are part of a route's URL path. The queryParamMap is a method that takes in an array of keys and is used to find specific parameters in the paramMap.
- [ ] paramMap is the legacy name from Angular 3. The new name is queryParamMap.
- [x] Both are Observables containing values from the requested route's URL string. The paramMap contains the parameter values that are in the URL path and the queryParamMap contains the URL query parameters.

#### Q4. Based on the following usage of the async pipe, and assuming the users class field is an Observable, how many subscriptions to the users Observable are being made?

```html
<h2>Names</h2>
<div *ngFor="let user of users | async">{{ user.name }}</div>
<h2>Ages</h2>
<div *ngFor="let user of users | async">{{ user.age }}</div>
<h2>Genders</h2>
<div *ngFor="let user of users | async">{{ user.gender }}</div>
```

- [ ] None. The async pipe does not subscribe automatically. None.
- [ ] None. The template syntax is not correct.
- [x] Three. There is one for each async pipe.
- [ ] One. The async pipe caches Observables by type internally.

#### Q5. How can you use the HttpClient to send a POST request to an endpoint from within an addOrder function in this OrderService?

```javascript
export class OrderService {
    constructor(private httpClient: HttpClient) { }

    addOrder(order: Order) {
      // Missing line
    }
}
```

- [ ] this.httpClient.url(this.orderUrl).post(order);
- [ ] this.httpClient.send(this.orderUrl, order);
- [ ] this.httpClient.post<Order>(this.orderUrl, order);
- [x] this.httpClient.post<Order>(this.orderUrl, order).subscribe();

#### Q6. What is the RouterModule.forRoot method used for?

- [ ] Registering any providers that you intend to use in routed components.
- [x] Registering route definitions at the root application level.
- [ ] Indicating that Angular should cheer on your routes to be successful.
- [ ] Declaring that you intend to use routing only at the root level.

#### Q7. Which DOM elements will this component metadata selector match on?

```javascript
@Component({
    selector: 'app-user-card',
    . . .
})
```

- [ ] Any element with the attribute app-user-card, such as `<div app-user-card></div>`.
- [ ] The first instance of `<app-user-card></app-user-card>`.
- [x] All instances of `<app-user-card></app-user-card>`.
- [ ] All instances of `<user-card></user-card>`.

#### Q8. What is the correct template syntax for using the built-in ngFor structural directive to render out a list of productNames?

- [ ]

```html
<ul>
  <li [ngFor]="let productName of productNames">{{ productName }}</li>
</ul>
```

- [ ]

```html
<ul>
  <li ngFor="let productName of productNames">{{ productName }}</li>
</ul>
```

- [x]

```html
<ul>
  <li *ngFor="let productName of productNames">{{ productName }}</li>
</ul>
```

- [ ]

```html
<ul>
  <? for productName in productNames { ?>
  <li>{{ productName }}</li>
  <? } ?>
</ul>
```

#### Q9. What are the two component decorator metadata properties used to set up CSS styles for a component?

- [ ] viewEncapsulation and viewEncapsulationFiles.
- [ ] There is only one and it is the property named css.
- [ ] css and cssUrl.
- [x] styles and styleUrls.

#### Q10. With the following component class, what template syntax would you use in the template to display the value of the title class field?

```javascript
@Component({
  selector: 'app-title-card',
  template: '',
})
class TitleCardComponent {
  title = 'User Data';
}
```

- [ ] `{{ 'title' }}`
- [x] `{{ title }}`
- [ ] `[title]`
- [ ] A class field cannot be displayed in a template via the template syntax.

#### Q11. What is the purpose of the valueChanges method on a FormControl?

- [ ] It is used to configure what values are allowed for the control.
- [ ] It is used to change the value of a control to a new value. You would call that method and pass in the new value for the form field. It even supports passing in an array of values that can be set over time.
- [ ] It returns a Boolean based on if the value of the control is different from the value with which it was initialized.
- [x] It is an observable that emits every time the value of the control changes, so you can react to new values and make logic decisions at that time.

#### Q12. What directive is used to link an `<a>` tag to routing?

- [ ] routeTo
- [x] routerLink
- [ ] routePath
- [ ] appLink

#### Q13. What is the Output decorator used for in this component class?

```javascript
@Component({
    selector: 'app-shopping-cart',
    . . .
})
export class ShoppingCartComponent {
    @Output() itemTotalChanged = new EventEmitter();
}
```

- [ ] It makes the `itemTotalChanged` class field public.
- [ ] It provides a way to bind values to the `itemTotalChanged` class field, like so: `<app-shopping-cart [itemTotalChanged]="newTotal"></app-shopping-cart>`.
- [x] It provides a way to bind events to the `itemTotalChanged` class field, like so: `<app-shopping-cart (itemTotalChanged)="logNewTotal($event)"></app-shopping-cart>`.
- [ ] It is simply a way to put a comment in front of a class field for documentation.

#### Q14. What is the difference between these two markup examples for conditionally handling display?

```html
<div *ngIf="isVisible">Active</div>
<div [hidden]="!isVisible">Active</div>
```

- [ ] The `ngIf` is shorthand for the other example. When Angular processes that directive, it writes a div element to the DOM with the hidden property.
- [ ] They are fundamentally the same.
- [x] The `ngIf` directive does not render the div in the DOM if the expression is false. The `hidden` property usage hides the div content in the browser viewport, but the div is still in the DOM.
- [ ] The `ngIf` is valid, but the use of the `hidden` property is wrong and will throw an error.

#### Q15. How can you disable the submit button when the form has errors in this template-driven forms example?

```html
<form #userForm="ngForm">
  <input type="text" ngModel name="firstName" required />
  <input type="text" ngModel name="lastName" required />
  <button (click)="submit(userForm.value)">Save</button>
</form>
```

- [ ]

```html
<button (click)="submit(userForm.value)" disable="userForm.invalid">Save</button>
```

- [x]

```html
<button (click)="submit(userForm.value)" [disabled]="userForm.invalid">Save</button>
```

- [ ]

```html
<button (click)="submit(userForm.value)" [ngForm.disabled]="userForm.valid">Save</button>
```

- [ ]

```html
<button (click)="submit(userForm.value)" *ngIf="userForm.valid">Save</button>
```

#### Q16. You want to see what files would be generated by creating a new contact-card component. Which command would you use?

- [x] ng generate component contact-card --dry-run
- [ ] ng generate component contact-card --no-files
- [ ] ng generate component component --dry
- [ ] ng generate component --exclude

#### Q17. Based on the following component, what template syntax would you use to bind the TitleCardComponent's titleText field to the h1 element title property?

```javascript
@Component({
  selector: 'app-title-card',
  template: '<h1 title="User Data"> {{titleText}}</h1>',
})
export class TitleCardComponent {
  titleText = 'User Data';
}
```

- [ ] `<h1 data-title="titleText">{{ titleText }}</h1>`
- [ ] `<h1 title="titleText">{{ titleText }}</h1>`
- [x] `<h1 [title]="titleText">{{ titleText }}</h1>`
- [ ] `<h1 titleText>{{ titleText }}</h1>`

#### Q18. What are Angular lifecycle hooks?

- [ ] loggers for tracking the health of an Angular app
- [ ] providers that can be used to track the instances of components
- [ ] built-in pipes that can be used in templates for DOM events
- [x] reserved named methods for components and directives that Angular will call during set times in its execution, and can be used to tap into those lifecycle moments

#### Q19. Pick the best description for this template syntax code:

```html
<span>Boss: {{job?.bossName}} </span>
```

- [ ] The ? is shorthand for the async pipe. The job value must be an Observable.
- [x] It is using the safe navigation operator (?) on the job field. If the job field is undefined, the access to the bossName will be ignored and no error will occur.
- [ ] There is an error in the template syntax. The ? is not valid here.
- [ ] It is diplaying the job value if it has one; otherwise it is displaying the bossName.

#### Q20. How would you configure a route definition for a UserDetailComponent that supports the URL path user/23 (where 23 represents the id of the requested user)?

- [x] `{ path: 'user/:id', component: UserDetailComponent }`
- [ ] `{ url: 'user/:id', routedComponent: UserDetailComponent }`
- [ ] `{ routedPath: 'user/:id', component: UserDetailComponent }`
- [ ] `{ destination: new UserDetailComponent(), route: 'user/:id' }`

#### Q21. What are the HostListener decorators and the HostBinding decorator doing in this directive?

```javascript
@Directive({
  selector: '[appCallout]',
})
export class CalloutDirective {
  @HostBinding('style.font-weight') fontWeight = 'normal';

  @HostListener('mouseenter')
  onMouseEnter() {
    this.fontWeight = 'bold';
  }

  @HostListener('mouseleave')
  onMouseLeave() {
    this.fontWeight = 'normal';
  }
}
```

- [x] They are setting the CalloutDirective.fontWeight field based on whether or not the mouse is over the DOM element. The HostListener then sets the font-weight CSS property to the fontWeight value.
- [ ] They are setting up the directive to check the DOM element that it is on. If it has event bindings added for mouse enter and leave it will use this code. Otherwise nothing will happen.
- [ ] This is an incorrect use of HostListener and HostBinding. The HostListener and HostBinding decorators do not do anything on directives; they work only when used on components.
- [ ] If the DOM element that this directive is placed on has the CSS property font-weight set on it, the mouseenter and mouseleave events will get raised.

#### Q22. What Angular template syntax can you use on this template-driven form field to access the field value and check for validation within the template markup?

```html
<input type="text" ngModel name="firstName" required minlength="4" />
<span *ngIf="">Invalid field data</span>
```

- [x] You can make use of a template reference variable and the exportAs feature that the ngModel directive has.
- [ ] You can use the ngModel directive in combination with the input field name.
- [ ] You can use a template reference variable for the HTML input element and then check the valid property off of that.
- [ ] It is not possible to get access to the field value with template-driven forms. You must use reactive forms for that.

#### Q23. What is the value type that will be stored in the headerText template reference variable in this markup?

```html
<h1 #headerText>User List</h1>
```

- [x] an Angular ElementRef, a wrapper around a native element
- [ ] the inner text of the `<h1>` element
- [ ] a header component class
- [ ] the native DOM element type of HTMLHeadingElement

#### Q24. What is the difference, if any, of the resulting code logic based on these two provider configurations?

```javascript
[{ provide: FormattedLogger, useClass: Logger }][{ provide: FormattedLogger, useExisting: Logger }];
```

- [ ] They are the same. Both will result in a new instance of Logger that is bound to the FormattedLogger token.
- [x] The useClass syntax tells the injector to make a new instance of Logger and bind that instance to the FormattedLogger token. The useExisting syntax refers to an already existing object instance declared as Logger.
- [ ] Both of them are wrong. A strong type connot be used for useClass or useExisting.
- [ ] They are the same. Both will result in the FormattedLogger token being an alias for the instance of Logger.

#### Q25. What is the purpose of the data property (seen in the example below) in a route configuration?

```javascript
   {
       path: 'customers',
       component: CustomerListComponent,
       data: { accountSection: true }
   }
```

- [ ] a key/value mapping for setting @Input values on the routed component instance
- [x] a way to include static, read-only data associated with the route that can be retrieved from the ActivatedRoute
- [ ] a property on the route that can be used to load dynamic data for the route
- [ ] an object that will get auto-injected into the routed component's constructor.

#### Q26. How does the built-in `ngIf` structural directive change the rendered DOM based on this template syntax?

```javascript
@Component({
  selector: 'app-product',
  template: '<div *ngIf="product">{{ product.name }}</div>',
})
export class ProductComponent {
  @Input() product;
}
```

- [ ] The `<div>` acts as a placeholder. If the product class field is "truthy," the `<div>` will get replaced by just the `product.name` value; if not, then nothing will get rendered.
- [ ] The `<div>` will always be rendered, and if the product field is "truthy," the `<div>` element will contain the `product.name` value; otherwise it will render the `<div>` element with no value in it.
- [ ] It produces an error, since ngIf is not a built-in structural directive.
- [x] If the product class field is "truthy," then the rendered DOM will include the `<div>` with the value of the `product.name` field. If it is not "truthy,' the rendered DOM will not contain the `<div>` element.

[Reference (angular.io)](https://angular.io/api/common/NgIf)

#### Q27. What does this code accomplish?

```javascript
@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule],
  bootstrap: [AppComponent],
})
export class AppModule {}

platformBrowserDynamic().bootstrapModule(AppModule);
```

- [ ] It executes a unit test for an NgModule.
- [ ] It provides a way to code the document structure of an Angular application. The @NgModule is a form of inline code commenting that gets ignored by the TypeScript compiler but will show up with special formatting in code editor applications.
- [ ] It declares an Angular module named AppModule and makes it available for lazy loading throughout the application.
- [x] It declares an Angular module named AppModule that contains a bootstrapped component named AppComponent. Then it registers that module with Angular, so the app can start up.

#### Q28. Which choice best describes what the _resolve_ property does in this route configuration?

```javascript
{
   path: ':id',
   component: UserComponent,
   resolve: {
     user: UserResolverService
   }
}
```

- [x] Prior to loading the _UserComponent_, the router will subscribe to the _Observable_ returned by a _resolve_ method in the _UserResolverService_. This technique can be used to get preloaded data for a _route_.
- [ ] After the _route_ is done resolving, and the component is loaded and rendered, the _UserResolverService_ will have a method named _user_ run that will clean up any open data connections.
- [ ] There is an error. The correct property name is _onResolve_.
- [ ] The _UserComponent_ will have a parameter in its constructor for _user_, and the _router_ will handle injecting in a value for that from a call to a _user_ method in the _UserResolverService_.

[Reference (angular.io)](https://angular.io/api/router/Resolve)

#### Q29. What is the purpose of the ContentChildren decorator in this component class?

```javascript
@Component({
 . . .
 template: '<ng-content></ng-content›'
})
export class TabsListComponent {
 @ContentChildren(TabComponent) tabs;
}
```

- [ ] If any _TabsComponent_ elements are added to the _TabsListComponent_ template, they will get put into the <ng-content> element at runtime.
- [ ] It creates _TabComponent_ components in the _TabsListComponent_ template when a _TabsListComponent_ is instantiated.
- [x] It provides access from within the component class to any _TabComponent_ components that were content projected into the <ng-content> for this component.
- [ ] It restricts the allowed elements that can be put into a _TabsListComponent_ element to allow only _TabComponent_ elements.

[Reference (betterprogramming.pub)](https://betterprogramming.pub/understanding-contentchildren-with-an-example-e76ce78968db)

#### Q30. In order for Angular to process components in an application, where do the component types need to be registered?

- [ ] within a script tag in the index.html file
- [ ] in an NgModule decorator metadata tag named _components_
- [ ] No registration is needed simply include the component files in an app directory.
- [x] in an NgModule decorator metadata property named _declarations_

[Reference (angular.io)](https://angular.io/guide/ngmodule-api#ngmodule-metadata)

#### Q31. What is the purpose of the `fixture.detectChanges()` call in this unit test?

```javascript
TestBed.configureTestingModule({
  declarations: [UserCardComponent],
});
let fixture = TestBed.createComponent(UserCardComponent);

fixture.detectChanges();

expect(fixture.nativeElement.querySelector('h1').textContent).toContain(
  fixture.componentInstance.title,
);
```

- [ ] It tracks any potential Ul changes and will fail the unit test if any are made.
- [ ] It is used to ensure component template stability across multiple unit tests in the entire test suite.
- [x] It forces Angular to perform change detection, which will render the _UserCardComponent_ before you can validate its template.
- [ ] It is used to log change-detection events to the console during unit test runs.

[Reference (angular.io)](https://angular.io/api/core/testing/ComponentFixture#detectChanges)

#### Q32. What will the URL segment look like based on the following call to the `Router.navigate` method when goToUser is passed the value 15?

```javascript
export class ToolsComponent {
 constructor (private router: Router) { }
 goToUser (id: number) {
   this.router.navigate(['user', id]);
 }
}
```

- [x] /user/15
- [ ] /user?id=15
- [ ] /user:15
- [ ] /user;id=15

[Reference (angular.io)](https://angular.io/api/router/Router#navigate)
