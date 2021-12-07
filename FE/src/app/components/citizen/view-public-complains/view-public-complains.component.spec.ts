import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPublicComplainsComponent } from './view-public-complains.component';

describe('ViewPublicComplainsComponent', () => {
  let component: ViewPublicComplainsComponent;
  let fixture: ComponentFixture<ViewPublicComplainsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPublicComplainsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPublicComplainsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
