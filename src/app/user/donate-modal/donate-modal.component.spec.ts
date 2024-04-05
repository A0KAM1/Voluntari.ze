import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DonateModalComponent } from './donate-modal.component';

describe('DonateModalComponent', () => {
  let component: DonateModalComponent;
  let fixture: ComponentFixture<DonateModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DonateModalComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DonateModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
