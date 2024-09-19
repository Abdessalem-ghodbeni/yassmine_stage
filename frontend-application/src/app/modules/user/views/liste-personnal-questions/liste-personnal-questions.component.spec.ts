import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListePersonnalQuestionsComponent } from './liste-personnal-questions.component';

describe('ListePersonnalQuestionsComponent', () => {
  let component: ListePersonnalQuestionsComponent;
  let fixture: ComponentFixture<ListePersonnalQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListePersonnalQuestionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListePersonnalQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
