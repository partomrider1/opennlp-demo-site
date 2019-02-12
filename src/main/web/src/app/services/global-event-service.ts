import { Injectable, EventEmitter } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable()
export class GlobalEventService {
    public refreshInput: EventEmitter<any> = new EventEmitter();
    private selectedFeature: string;
    
    public refreshInputState(input) {
        this.selectedFeature = input;
        this.refreshInput.emit({prop: input});
    }

    public getSelectedFeature() {
        return this.selectedFeature;
    }
}