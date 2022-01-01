import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Task } from '../../Task';
import { UiService } from 'src/app/services/ui.service';
import { faLessThanEqual } from '@fortawesome/free-solid-svg-icons';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

  @Output() onAddTask: EventEmitter<Task> = new EventEmitter();

  text: string = '';
  day: string = '';
  remainder: boolean = false;
  showAddTask: boolean = false;
  subscription !: Subscription;
  constructor(private uiService: UiService) {
    this.subscription = this.uiService.onToggle().subscribe((value) => this.showAddTask = value);
  }

  ngOnInit(): void {
  }
  onSubmit() {
    console.log("submitting")
    if (!this.text) {
      alert("Please add a task!")
      return;
    }

    const newTask = {
      text: this.text,
      day: this.day,
      remainder: this.remainder
    }
    this.onAddTask.emit(newTask);
    this.text = '';
    this.day = '';
    this.remainder = false;
  }
}
