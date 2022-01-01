import { Component, OnInit } from '@angular/core';
import { TaskService } from 'src/app/services/task.service';
import { Task } from 'src/app/Task';
@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
  tasks: Task[] = [];
  constructor(private taskService: TaskService) { }

  ngOnInit(): void {
    this.taskService.getTasks().subscribe((taskArr) => {
      this.tasks = taskArr;
    });
  }

  deleteTask(task: Task) {
    console.log("task from taskComponent " + JSON.stringify(task));
    this.taskService.deleteTask(task).subscribe(() => {
      this.tasks = this.tasks.filter(t => t.id !== task.id);
    })
  }

  toggleRemainder(task: Task){
    task.remainder = !task.remainder;
    console.log("toggling from task component, updating the task "+JSON.stringify(task))
    this.taskService.updateTask(task).subscribe()
  }

  addTask(task : Task){
    console.log("adding task "+ JSON.stringify(task))
    this.taskService.addTask(task).subscribe((task)=>{
      this.tasks.push(task)
    });
  }
}
