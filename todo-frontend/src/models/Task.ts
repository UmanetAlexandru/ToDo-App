export class TaskDTO {
    id!: number;
    name!: string;
    description!: string;
    completed = false;
}

export default class Task extends TaskDTO {
    constructor(dto: TaskDTO) {
        super();
        Object.assign(this, dto);
    }
}
