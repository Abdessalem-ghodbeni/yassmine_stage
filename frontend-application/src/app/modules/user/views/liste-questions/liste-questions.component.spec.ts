import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeQuestionsComponent } from './liste-questions.component';

describe('ListeQuestionsComponent', () => {
  let component: ListeQuestionsComponent;
  let fixture: ComponentFixture<ListeQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeQuestionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListeQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
