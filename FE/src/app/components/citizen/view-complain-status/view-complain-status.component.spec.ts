import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewComplainStatusComponent } from './view-complain-status.component';

describe('ViewComplainStatusComponent', () => {
  let component: ViewComplainStatusComponent;
  let fixture: ComponentFixture<ViewComplainStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewComplainStatusComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewComplainStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
