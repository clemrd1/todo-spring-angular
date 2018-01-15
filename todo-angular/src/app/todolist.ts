import { Todo } from './todo';

export class TodoList {
  listId: number;
  name: string;
  archived: boolean;
  archivedDate: Date;
  todos: Array<Todo>;
}
