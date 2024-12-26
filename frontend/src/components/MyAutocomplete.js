import React from 'react';
import Autocomplete from '@mui/material/Autocomplete';
import TextField from '@mui/material/TextField';
import { createFilterOptions } from '@mui/material/Autocomplete'; // Đảm bảo nhập đúng

const filter = createFilterOptions();

const options = [
    { title: 'Option 1' },
    { title: 'Option 2' },
    { title: 'Option 3' },
];

function MyAutocomplete() {
    return (
        <Autocomplete
            options={options}
            getOptionLabel={(option) => option.title}
            filterOptions={(options, { inputValue }) => {
                return filter(options, inputValue);
            }}
            renderInput={(params) => <TextField {...params} label="Choose an option" />}
        />
    );
}

export default MyAutocomplete;