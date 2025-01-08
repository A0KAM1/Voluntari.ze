import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendToEventModalComponent } from '../attend-to-event-modal.component';

describe('AttendToEventModalComponent', () => {
  let component: AttendToEventModalComponent;
  let fixture: ComponentFixture<AttendToEventModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AttendToEventModalComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(AttendToEventModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
