import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeComplainComponent } from './make-complain.component';

describe('MakeComplainComponent', () => {
  let component: MakeComplainComponent;
  let fixture: ComponentFixture<MakeComplainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeComplainComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeComplainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
