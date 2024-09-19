import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoserQuestionComponent } from './poser-question.component';

describe('PoserQuestionComponent', () => {
  let component: PoserQuestionComponent;
  let fixture: ComponentFixture<PoserQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PoserQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PoserQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
