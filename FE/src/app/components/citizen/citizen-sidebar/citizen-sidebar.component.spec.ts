import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CitizenSidebarComponent } from './citizen-sidebar.component';

describe('CitizenSidebarComponent', () => {
  let component: CitizenSidebarComponent;
  let fixture: ComponentFixture<CitizenSidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CitizenSidebarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CitizenSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
